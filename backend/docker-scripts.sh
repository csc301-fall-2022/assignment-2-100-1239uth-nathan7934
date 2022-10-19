docker build -t 1239uth/shopping-list:1.0 .
docker run --name shopping-list -p 8080:8080 uthman/shopping-list
docker push 1239uth/shopping-list:latest