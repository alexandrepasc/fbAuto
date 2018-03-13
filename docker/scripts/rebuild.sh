#!/bin/bash

java -jar createDockerFile.jar

docker build --rm -t=fbauto ../.
