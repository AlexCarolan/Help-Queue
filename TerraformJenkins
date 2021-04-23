terraform {
  required_providers {
    aws = {
      source  = "hashicorp/aws"
      version = "~> 3.27"
    }
  }
}

provider "aws" {
  profile = "default"
  region  = "eu-west-1"
}

resource "aws_instance" "app_server" {
  ami           = "ami-08bac620dc84221eb"
  instance_type = "t2.small"
  key_name      = "Help-Queue-KeyPair"

  tags = {
    Name = "Jenkins"
  }
}
