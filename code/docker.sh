# List all containers
docker ps -aq

docker rm $(docker ps -a -q) # remove all containers
docker rmi $(docker images -q) # remove all images

# Run shell or execute command in container
docker exec <container_name> <command>
docker exec -it my_container_web_1 /bin/bash


# Docker Overview
# You BUILD a Dockerfile and create an IMAGE
# You RUN an image, which starts a CONTAINER
# You EXEC commands in a running CONTAINER
# YOU COMPOSE many IMAGES into many CONTAINERS

## Base Images
# RUN a base image (local or remote)
# or use it as the base image of your dockerfile:
# * base image: "FROM <image>")
# each line is a new image/layer
# * ADD files or a VOLUME

## Run Images
# -d for --detach
docker run -p 80:80 <image_name>:<version> # If not a local image, fetch from remote
# Run a with volume
docker run -p <ports:ports> <image_name>:<version> --volume path/to/local_vol:path/to/container_vol

## Build Images
# BUILD an image based off of a Dockerfile  (-t = --tag)
docker build -t <image_name>:<image_version> ./path/to/Dockerfile
docker build -t myapp:1.0 --rm --force-rm ./Dockerfile

## Docker Compose
docker-compose up # (build and start services)
# if dockerfile or build directory changes, rebuild
docker-compose build # (only build, don't start services)
