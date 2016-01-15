JDK_OPTS="-Xmx200M "
base_dir=/var/hbc/search_poisyc
for file in $base_dir/lib/*.jar;
do
  CLASSPATH=$CLASSPATH:$file
done

CLASSPATH=$CLASSPATH:search-solrdata.jar
java $JDK_OPTS  -cp $CLASSPATH com.hbc.api.trade.timer.core.SdataSycMain  $base_dir/resfiles/
