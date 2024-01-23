
## Deploy gcp resources through terraform

### 1. Authenticate gcloud
```
Install gcloud cli if not installed https://cloud.google.com/sdk/docs/install 
a) export GOOGLE_APPLICATION_CREDENTIALS=key.json
b) gcloud auth activate-service-account --key-file=$GOOGLE_APPLICATION_CREDENTIALS
```

### 2. Install 
  - terraform in your local
  https://askubuntu.com/questions/983351/how-to-install-terraform-in-ubuntu
  - Install gcloud kubectl
  https://cloud.google.com/kubernetes-engine/docs/how-to/cluster-access-for-kubectl#install_kubectl
  - Install helm 
   ```snap install helm --classic```
  - Keep the service account json key (key.json) inside terraform directory and it should have the permission
   ```
    Cloud Datastore Owner
    Cloud Functions Admin
    Cloud SQL Admin
    Compute Admin
    Container Registry Service Agent
    Create Service Accounts
    Firestore Service Agent
    Kubernetes Engine Admin
    Project IAM Admin
    Pub/Sub Admin
    Secret Manager Admin
    Secret Manager Secret Accessor
    Security Reviewer
    Service Account Admin
    Service Account User
    Service Usage Admin
    Storage Admin
    Storage Object Admin
   ```
 - Check the [terraform.tfvars](terraform%2Fterraform.tfvars) file for the gcp project and credentials values and path.

### 3. run -

- terraform init
- terraform plan
- terraform apply

To destroy the all gcp resources
- terraform destroy

### 4. Resources Created on Google Cloud Platform (GCP)
After a successful Terraform execution, the resources listed below are generated on Google Cloud Platform (GCP)
- Google Kubernetes Engine
  - car-demo-gke
  - axon-server-gke
  - external-secret-car-demo-gke
  - elasticsearch-server-gke
- Firestore
- Event based Cloud Function
- Pub/Sub
  - Subscriptions
    - inventory_subscription
    - vehicle_subscription
    - shipment_subscription
  - Topics
    - shipment-notification
    - vehicle
- MySql instance with databases
  - order_db
  - inventory_db
  - payment_db
  - shipment_db
- Secret Manager
  - car-demo-secret

