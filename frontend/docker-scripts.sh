docker build -t 1239uth/shopping-list-frontend:1.0 .
docker run --name shopping-list-frontend -p 3000:8081 1239uth/shopping-list-frontend:1.0
#docker push 1239uth/shopping-list:latest