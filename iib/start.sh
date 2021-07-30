docker build . -t iib_advanced
CONTAINER_NAME=myContainer
docker stop $CONTAINER_NAME
docker rm $CONTAINER_NAME
docker run --name $CONTAINER_NAME -d -v  ${PWD}/BARs:/tmp/BARs -e LICENSE=accept -e NODENAME=MYNODE -e SERVERNAME=MYSERVER -p 7800:7800 iib_advanced
