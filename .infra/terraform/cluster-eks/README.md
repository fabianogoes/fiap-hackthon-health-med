# AWS EKS Cluster by Terraform

[![Terraform](https://github.com/fabianogoes/fiap-tech-challenge-cluster-eks-terraform/actions/workflows/terraform.yml/badge.svg)](https://github.com/fabianogoes/fiap-tech-challenge-cluster-eks-terraform/actions/workflows/terraform.yml)

Referencia: https://mateusmuller.me/posts/como-criar-cluster-eks-com-terraform/

- [ ] Iniciar AWS Academy
- [ ] Atualizar aws cli credentials
- [ ] Verificar conexão com a AWS `aws sts get-caller-identity`
- [ ] Atualizar `variables.tf` com as subnets, vpc e security group
- [ ] Executar `terraform init -upgrade`
- [ ] Executar `terraform plan`
- [ ] Executar `terraform apply`
- [ ] Verificar no console da AWS se o Cluster foi criado com o node group

## Comandos AWS CLI

### Listar todos das VPCs de uma region

```shell
aws ec2 describe-vpcs --region us-east-1 |grep VpcId
```
output: `VpcId: "vpc-08b1fbde208e597a2"`

### Listar todas as subnets de uma VPC

> `vpc-08b1fbde208e597a2` = deve ser a nova vpc

```shell
aws ec2 describe-subnets --filter Name=vpc-id,Values=vpc-08b1fbde208e597a2 --query 'Subnets[?AvailabilityZone==`us-east-1a`].SubnetId'
aws ec2 describe-subnets --filter Name=vpc-id,Values=vpc-08b1fbde208e597a2 --query 'Subnets[?AvailabilityZone==`us-east-1b`].SubnetId'
aws ec2 describe-subnets --filter Name=vpc-id,Values=vpc-08b1fbde208e597a2 --query 'Subnets[?AvailabilityZone==`us-east-1c`].SubnetId'
 ```

## Testando o Cluster

```shell
aws sts get-caller-identity
aws eks --region us-east-1 update-kubeconfig --name health_med_eks_cluster
kubectl cluster-info
```
