(defproject clojurews "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url  "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.5.1"]

                 [ring/ring-core "1.1.8"]
                 [compojure "1.1.5"]
                 [http-kit "2.1.17"]


                 [org.clojure/tools.logging "0.2.6"]
                 ]


  :min-lein-version "2.0.0"
  :resource-paths ["resources"]
  :source-paths ["src"]
  :java-source-paths ["src-java"]
  :aot [clojurews.core] :main clojurews.core
  :global-vars {*warn-on-reflection* true}
  )
