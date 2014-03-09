# Gibberish logger
## A showcase for Logstash and Kibana

 This tiny app generates a log file which shows a few use cases of Logstash + Kibana.
 It generates quite some normal noise plus a few special cases:

 - A disaster: A high peak of errors occuring at selected time, for selected duration.
 - A batch job: A regular task which can be easily spotted using the "BatchStart" query. There is an artificial 'error'
 which causes one batch execution to be skipped, just to show how easily one can spot such anomalies visually on a Kibana
 graph.

## How to build
Use `sbt assembly` task to prepare an executable flatjar.

## How to run
Use `sbt run` in sbt console or execute the flatjar with `GibberishLogger.scala` as main class. Default settings will
generate 1 hour of log file called gibberish.log.

## Starting the Vagrant box and running Logstash + Kibana
I assume you already know what is [Vagrant](http://www.vagrantup.com/) and that you understand all its basics.

* Copy your generated log file to `provisioning/cookbooks/logstash_showcase/files/default/custom.log'. This file will be mounted when you start your Vagrant box and automatically scanned by logstash.
* Customize your Vagrant box (**Tested only on Ubuntu!**) in Vagrantfile.
* Run following commands to fetch all needed packages and start the box:  
`bundle install`  
`berks install`  
`vagrant up`  
Tested on Ruby 1.9.3 with Bundler 1.5. Make sure that your ports 1443, 9200, 9201 are open.

## Using Kibana
* Use `vagrant ssh` to access the shell. Go to `var/log`, become root and copy contents of `custom.log` into `gibberish.log` using for example `cat custom.log >> gibberish.log`.
Open https://localhost:1443/ in your browser. User `logstash:qwerty123` as default credentials for Basic HTTP Authentication. When Kibana opens, select a time period to match time of generated log data. You should see your log entries.
