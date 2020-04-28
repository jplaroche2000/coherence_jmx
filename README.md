# Load a custom MBean to expose GAR version
This project describes the different parts required to expose the verison of a GAR in Coherence MBeans

## Create the custom MBean

Create a class and an inerface with a version getter/setter. see [GARVersion.java](https://github.com/jplaroche2000/coherence_jmx/blob/master/src/main/java/ca/kafeine/coherence/listener/GARVersion.java) and [GARVersionMBean.java](https://github.com/jplaroche2000/coherence_jmx/blob/master/src/main/java/ca/kafeine/coherence/listener/GARVersionMBean.java)


## Create the GAR life cycle listener



## Declare the listener in coherence-application.xml

## Inject custom attribute in GAR MANIFEST file


1. kubectl: create cluster
```
gcloud container clusters create k8s-coherence-cluster --machine-type=g1-small --num-nodes=3 --no-enable-autoupgrade --zone northamerica-northeast1-a --project superb-reporter-256719
```

2. kubectl: login to cluster 
```
gcloud container clusters get-credentials k8s-coherence-cluster --zone northamerica-northeast1-a --project superb-reporter-256719 
```
