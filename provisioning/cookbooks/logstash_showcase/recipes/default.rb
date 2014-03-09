include_recipe "logstash_showcase::periodic_update"
include_recipe "logstash_showcase::logstash_agent"

cookbook_file "/var/log/gibberish.log" do
  source "gibberish.log"
  owner "logstash"
  group "logstash"
  mode "0750"
end

# Following file can be copied to gibberish.log to make logstash import log data
# You can simply use "cat custom.log >> gibberish.log" to simulate log writing.
cookbook_file "/var/log/custom.log" do
  source "custom.log"
  owner "logstash"
  group "logstash"
  mode "0750"
end


include_recipe "logstash_showcase::redis_server"
include_recipe "logstash_showcase::elasticsearch"
include_recipe "logstash_showcase::logstash_indexer"
include_recipe "logstash_showcase::kibana"