#!/bin/bash

cd "../../kubernetes-stack/yamls-post-chaos/istio"

kubectl delete -f "istio-addons/"

