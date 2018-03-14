#!/bin/bash

value=$(<../config)
echo "$value"

docker run -it -d --name fbauto -v $value:/home/seluser/fbAuto/app fbauto /bin/bash
