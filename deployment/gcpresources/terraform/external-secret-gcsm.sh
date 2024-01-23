#!/bin/sh

GKE_CLUSTER="$1"
REGION="$2"
# Authenticate car-demo-gke
gcloud container clusters get-credentials "$GKE_CLUSTER" --region "$REGION"
# Create gcpsm-secret with credential
kubectl create secret generic gcpsm-secret --from-file=secret-access-credentials=key.json

# Create GCP secret Key
kubectl create secret generic gac-key --from-file key.json

# Add external-secret using helm chart
helm repo add external-secrets https://charts.external-secrets.io
# install external-secrets/external-secrets CRD
helm install external-secrets external-secrets/external-secrets -n default  --set installCRDs=true