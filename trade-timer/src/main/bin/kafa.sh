JDK_OPTS="-Xmx200M "
base_dir=/data/server/tradetimer
for file in $base_dir/lib/*.jar;
do
  CLASSPATH=$CLASSPATH:$file
done

CLASSPATH=$CLASSPATH
java $JDK_OPTS  -cp $CLASSPATH  com.hbc.api.trade.timer.core.RedisQueueStarter  $base_dir/resfiles/
