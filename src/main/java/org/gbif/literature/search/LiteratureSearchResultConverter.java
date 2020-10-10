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

import org.gbif.literature.api.LiteratureSearchResult;
import org.gbif.literature.api.LiteratureSuggestResult;

import org.elasticsearch.search.SearchHit;
import org.springframework.stereotype.Component;

@Component
public class LiteratureSearchResultConverter
    implements SearchResultConverter<LiteratureSearchResult, LiteratureSuggestResult> {

  @Override
  public LiteratureSearchResult toSearchResult(SearchHit searchHit) {
    return null;
  }

  @Override
  public LiteratureSuggestResult toSearchSuggestResult(SearchHit searchHit) {
    return null;
  }
}
