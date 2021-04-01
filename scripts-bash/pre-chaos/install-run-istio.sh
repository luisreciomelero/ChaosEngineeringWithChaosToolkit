#!/bin/bash

istioctl install --set profile=demo -y

sleep 30

kubectl label namespace default istio-injection=enabled

cd "../../kubernetes-stack/yamls-pre-chaos/istio"

kubectl apply -f "istio-addons/"

cd "../../../../scripts-bash"