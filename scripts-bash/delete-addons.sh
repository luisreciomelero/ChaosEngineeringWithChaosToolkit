#!/bin/bash

cd "../kubernetes-stack/yamls"

kubectl delete -f "istio-addons/"

cd "../../scripts-bash/"