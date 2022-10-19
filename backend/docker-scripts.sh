docker build -t 1239uth/shopping-list-backend:1.0 .
docker run --name shopping-list-backend -p 8080:8080 1239uth/shopping-list-backend:1.0
#docker push 1239uth/shopping-list:latest