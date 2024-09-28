variable "account_id" {}

variable "region" {
    default = "us-east-1"
}

variable "vpcId" {
    default = "vpc-0edd2c2df86ed4254"
}

variable "subnetA" {
    default = "subnet-05d60cb57ec9c2e67"
}

variable "subnetB" {
    default = "subnet-08dc0fa3a89069df7"
}

variable "subnetC" {
    default = "subnet-0e8616577df770dd1"
}

variable "sgId" {
    default = "sg-0a276f8fc9d911bb7"
}

variable "nodeName" {
    default = "ng-health-med"
}

variable "accessConfig" {
    default = "API_AND_CONFIG_MAP"
}

variable "policyArn" {
    default = "arn:aws:eks::aws:cluster-access-policy/AmazonEKSAdminPolicy"
}