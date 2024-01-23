# Car Demo
We are developing an eCommerce platform dedicated to the sale of cars. Users will have the capability to search for products, place orders through a shopping cart, and complete payments. The platform will also incorporate inventory management for efficient tracking. Additionally, a secure centralized data platform will be established to support data analytics and facilitate the implementation of AI/ML use cases.

Key technical benefits
--
- Developed the platform by following Domain Driven Design principle.
- Developed microservices with Axon Framework. Each microservice solve a business problem or representing a domain for example order, product, cart, inventory etc.
- Front end Angular - Angular
- The WebFlux framework facilitates non-blocking, event-driven processes for car user interfaces (Dashboards).
- The data platform is built on Databricks, a unified platform for data engineering, data governance, data analytics, and AI/ML.
- Established a secure integration of Microsoft powerBI with databricks for visualization and reporting.
- Ready to deploy in the GCP
- Run from Docker containers

System functionality
--
Car-demo system provides the following functionality:
- **Ordering:** receives a customer order, order details including items, item quantities
- **Payment:** Involves the user verification, and settlement of transactions
- **Shipping:** Streamline outgoing shipments to customers with pricing on shipments and confirmation notifications
- **Shopping cart:** Allowing customers to select items from the store's website
- **Inventory management:** Manage car [product] that are available for sale
- **Databricks:** The Databricks Pipeline ingests data from a GCP Pub/Sub topic, stores it in a Databricks Delta Lake table, and utilizes Unity Catalog as a comprehensive governance solution for data and AI within the lakehouse environment.
- **PowerBI:** Integrating Databricks with PowerBI to enhance visualization and reporting capabilities.

Architecture
--
![arch.png](documentation%2Farch.png)

Get the source code:
-------------------
Clone the repository:

	 $ git clone https://github.com/NashTech-Labs/car-demo.git


Getting Started for Local
--
Let's execute the application using the default local settings, which include a separate .env file for each service, and configurations. In the root of the car-demo, there is a *local-dev/docker-compose.yml* file that enables us to launch necessary resources such as MySQL, Axon Server, and Elastic Server. The following steps outline the setup and launch process for the applications.

**1) Run docker-compose**
```
Resource Launches
$ docker-compose up -d

Resource Down
$ docker-compose down
```
**2) Set GOOGLE_APPLICATION_CREDENTIALS as an environment variable**

This property is utilized for connecting and authenticating GCP resources, and the following services rely on it:
- Elasticsearch service
- Inventory service
- Shipment service 

You can include this environment property in IntelliJ by navigating to the upper right corner, selecting Edit Configurations, and then going to Environmental Variables.

**3) Run Services**

The listed services are operate sequentially.

***Phase-1***
- admin-service
- inventory-service 
- order-service  [README.md](order-service%2FREADME.md) 
- payment-service  [README.md](payment-service/README.md)
- shipment-service 
- elasticsearch

***Phase-2***
- cart-service

***Phase-3***
- car-ui

Now open a browser on this urls

Application  | Url
------------- | -------------
Headless  admin service |  localhost:8080
Headless  cart service |  localhost:9094
Headless  elasticsearch service |  localhost:9095
Headless  inventory service |  localhost:9091
Headless  order service |  localhost:9090
Headless  payment service |  localhost:9092
Headless  shipment service |  localhost:9093
Car Ui  | localhost:8080


GCP Infrastructure and Deployment
-- 
### GCP Infrastructure by using Terraform
To deploy the GCP infrastructure required, follow the steps in deployment readme: 
[readme.md](deployment%2Fgcpresources%2Freadme.md) deployment/gcpresources/readme.md

### Deploy Services via Github Action
- Add required secrets in https://github.com/NashTech-Labs/car-demo/settings/secrets/actions
- GKE_KEY : the service account json key having these permission:
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
- GKE_PROJECT: Project name
github workflow for CI/CD can be found here:
[car-demo-pipeline.yml](.github%2Fworkflows%2Fcar-demo-pipeline.yml)

- Keep the service name in [projects-changes-deploy.txt](projects-changes-deploy.txt) which you want to deploy.

When a commit push to main branch it will start the deployment of the services/project mentioned in 
file *projects-changes-deploy.txt*.
deployments steps for each project are mentioned in [apps-deployment-script.sh](apps-deployment-script.sh)

**Note:**

***common:*** It consists of common utility, events, commands and models used across multiple projects.   
you will find the deployment step of common in [apps-deployment-script.sh](apps-deployment-script.sh)
it will do clean package and push the package on github.
so, Each time you make the changes in common upgrade the version in its *pom.xml* and also
update same version of common in each projects pom.xml

####  Obtain a GCP Service Account json key 
The process to obtain a JSON key for a Google Cloud Platform (GCP) service account involves the following steps:
```arm
1. Open the Google Cloud Console: Navigate to the Google Cloud Console at https://console.cloud.google.com/.

2. Access the IAM & Admin Section: In the console, locate and click on the "IAM & Admin" section.

3. Choose the Project: Select the project for which you want to create a service account or ensure that you are working within the correct project.

4. Navigate to Service Accounts: In the IAM & Admin section, find and click on "Service accounts" to view the existing service accounts for the selected project.

5. Create a New Service Account: Click on the "Create Service Account" button to initiate the process of creating a new service account.

6. Provide Details: Enter a name for your service account, add a description (optional), and choose the appropriate role(s) for the service account based on your project's needs.

7. Generate a Key: After creating the service account, click on the "Create Key" button. Choose the key type as JSON, and a JSON key file will be generated and downloaded to your device.
```
Keep the JSON Key Secure: Ensure that you securely store the downloaded JSON key file, as it contains sensitive information and grants access to your GCP resources.

#### CI/CD Pipeline for GCP
The [ReadMe.md](GCP_Ci-Cd_Pipeline_README.md) file contains the steps for deploying the system.


#### Check Deployment of Kubernetes on GCP
To verify the deployment of Kubernetes on Google Cloud Platform (GCP), follow these steps:
```arm
1. Access GCP Console:
   Log in to the Google Cloud Console at https://console.cloud.google.com/.

2. Navigate to Kubernetes Engine:
   In the console, go to the "Kubernetes Engine" section.

3. Select Cluster:
   Choose the Kubernetes cluster you want to check.

4. Review Cluster Details:
   Examine the cluster details, including the status, version, and other relevant information.

5. Verify Nodes:
   Confirm that the nodes in your cluster are in the "RUNNING" state.

6. Check Workloads:
   Examine the workloads running on the cluster to ensure that your applications and services are deployed as expected.

7. Inspect Pods:
   Review the status of individual pods within the cluster to ensure they are running without errors.

8. Examine Services:
   Confirm that the Kubernetes services associated with your applications are accessible and running correctly.

9. Monitor Events:
   Check the cluster events for any warnings or errors that might indicate deployment issues.

10. Test Connectivity:
    Verify that you can connect to your deployed applications and services from external sources if applicable.

11. Check External Access:
    If your applications are supposed to be externally accessible, confirm that external access is properly configured.

12. Review Logs:
    Examine logs for applications and services to identify any issues or error messages.
```

Data Analytics and Visualization
--
For the analytical purpose, the insightful data generated by microservices brings to the databricks
Lakehouse platform.
find the details here [README.md](databricks%2FREADME.md) 

Documentation
--
- Demo session [available](https://harveynashvn-my.sharepoint.com/personal/sushant_gupta_nashtechglobal_com/_layouts/15/stream.aspx?id=%2Fpersonal%2Fsushant%5Fgupta%5Fnashtechglobal%5Fcom%2FDocuments%2FJava%2Dcompetency%2Dartifacts%2Dinternal%2Dproject%2FRecordings%2Fdemo%5F%20automobile%20eCommerce%20platform%2D20231206%5F142041%2DMeeting%20Recording%2Emp4&referrer=StreamWebApp%2EWeb&referrerScenario=AddressBarCopied%2Eview&isSPOFile=1&OR=Teams%2DHL&CT=1705060735545&clickparams=eyJBcHBOYW1lIjoiVGVhbXMtRGVza3RvcCIsIkFwcFZlcnNpb24iOiI0OS8yMzExMzAyODcyMCIsIkhhc0ZlZGVyYXRlZFVzZXIiOmZhbHNlfQ%3D%3D)
- Demo Presentation [available](https://github.com/NashTech-Labs/car-demo/blob/main/documentation/automobile%20ecommerce-platform.pptx) 
