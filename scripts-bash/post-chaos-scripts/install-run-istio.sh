#!/bin/bash

istioctl install --set profile=default --set meshConfig.accessLogFile=/dev/stdout -y

sleep 30

kubectl label namespace default istio-injection=enabled 

cd "../../kubernetes-stack/yamls-post-chaos/istio"

kubectl apply -f "istio-addons/"

