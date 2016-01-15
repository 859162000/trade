JDK_OPTS="-Xmx500M "
base_dir=/data/server/datatransfer
for file in $base_dir/lib/*.jar;
do
  CLASSPATH=$CLASSPATH:$file
done

CLASSPATH=$CLASSPATH:search-solrdata.jar
java $JDK_OPTS   -DzkHosts=172.16.1.22:12181,172.16.1.23:12181,172.16.1.24:12181 -cp $CLASSPATH com.hbc.data.trade.transfer.core.OrderIncreaseMain  $base_dir/resfiles/
