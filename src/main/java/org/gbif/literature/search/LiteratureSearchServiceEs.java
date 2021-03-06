/*
 * Copyright 2020 Global Biodiversity Information Facility (GBIF)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.literature.search;

import org.gbif.api.model.common.search.SearchResponse;
import org.gbif.literature.api.LiteratureSearchParameter;
import org.gbif.literature.api.LiteratureSearchRequest;
import org.gbif.literature.api.LiteratureSearchResult;

import java.io.IOException;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LiteratureSearchServiceEs implements LiteratureSearchService {

  private final RestHighLevelClient restHighLevelClient;
  private final LiteratureEsResponseParser esResponseParser;
  private final EsSearchRequestBuilder<LiteratureSearchParameter> esSearchRequestBuilder;
  private final String index;

  public LiteratureSearchServiceEs(
      @Value("${elasticsearch.index}") String index,
      RestHighLevelClient restHighLevelClient,
      LiteratureEsResponseParser esResponseParser,
      EsSearchRequestBuilder<LiteratureSearchParameter> esSearchRequestBuilder) {
    this.index = index;
    this.restHighLevelClient = restHighLevelClient;
    this.esResponseParser = esResponseParser;
    this.esSearchRequestBuilder = esSearchRequestBuilder;
  }

  @Override
  public SearchResponse<LiteratureSearchResult, LiteratureSearchParameter> search(
      LiteratureSearchRequest literatureSearchRequest) {
    try {
      SearchRequest searchRequest =
          esSearchRequestBuilder.buildSearchRequest(literatureSearchRequest, true, index);
      return esResponseParser.buildSearchResponse(
          restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT),
          literatureSearchRequest);
    } catch (IOException ex) {
      throw new RuntimeException(ex);
    }
  }
}
