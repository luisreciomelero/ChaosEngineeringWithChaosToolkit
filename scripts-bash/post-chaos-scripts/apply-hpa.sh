#!/bin/bash

cd "../../../kubernetes-stack/yamls-post-chaos/horizontalPodAutoScalers"

kubectl apply -f "metrics-components.yaml,mg-hpa.yaml,ms-read-hpa.yaml"