#!/bin/bash

value=$(<../config)
echo "$value"

docker run -it -d --name fbauto -v $value:/home/seluser/fbAuto/app fbauto /bin/bash

docker exec -it fbauto bash -c "bash permissions.sh"

docker exec -it fbauto bash -c "mv config.xml app/config.xml"
