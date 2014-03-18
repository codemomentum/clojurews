# clojurews

A Sample Clojure project to demonstrate how to build functional Rest Services


## Usage

There are 3 routes defined
pprint
pprint_debug
pprint_intercept

-->
curl -X POST  http://localhost:8080/pprint_debug/ -d '{ :url "http://www.searchly.com/" :max_urls_per_cycle 100 :es { :url "http://localhost:9200/" :index "index1" :type "document" } :url_include_patterns ["posts"] :url_exclude_patterns ["Catalog" "catalog"] }'

{:url "http://www.searchly.com/",
:max_urls_per_cycle 100,
:es
{:url "http://localhost:9200/", :index "index1", :type "document"},
:url_include_patterns ["posts"],
:url_exclude_patterns ["Catalog" "catalog"]}

## License

Copyright © 2014 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
