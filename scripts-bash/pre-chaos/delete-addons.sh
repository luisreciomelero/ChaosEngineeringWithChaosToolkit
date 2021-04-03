#!/bin/bash

cd "../../kubernetes-stack/yamls-pre-chaos/istio"

kubectl delete -f "istio-addons/"

cd "../../../../scripts-bash/"