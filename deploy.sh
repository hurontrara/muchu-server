REPOSITORY=/home/ubuntu/cicdproject/springbootapp
cd $REPOSITORY

APP_NAME=muchu
JAR_NAME=$(ls $REPOSITORY/build/libs/ | grep '.jar' | tail -n 1)
JAR_PATH=$REPOSITORY/build/libs/$JAR_NAME

CURRENT_PID=$(pgrep -f $APP_NAME)

echo "> $CURRENT_PID"

if [ -z "$CURRENT_PID" ]
then
  echo "> 종료할 것 없음."
else
  echo "> kill -9 $CURRENT_PID"
  kill -15 "$CURRENT_PID"
  sleep 5
fi

echo "> $JAR_PATH 배포"
nohup java -jar $JAR_PATH > /dev/null 2> /dev/null < /dev/null &

echo "> ----------------------------------"