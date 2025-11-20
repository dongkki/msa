#!/bin/sh

docker container rm -f license 2> /dev/null || true
docker run --name license -d \
-p8080:8080 \
--memory=1g \
--network oracle-network \
license