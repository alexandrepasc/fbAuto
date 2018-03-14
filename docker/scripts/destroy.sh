#!/bin/bash

if [[ "$1" == "all" ]] ; then
	docker rm -f fbauto
	docker rmi -f fbauto
else
	docker rm -f fbauto
fi
