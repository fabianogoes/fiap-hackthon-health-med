resource "aws_db_instance" "health_med_db" {
    identifier = "health_med_db"
    allocated_storage = 5
    engine = "postgres"
    engine_version = "16.1"
    parameter_group_name = "default.postgres16"
    instance_class = "db.t3.micro"
    db_name = "health_med_db"
    username = "health_med_usr"
    password = "health_med_pwd"

    vpc_security_group_ids = [aws_security_group.instance.id]
    
    publicly_accessible = true
    skip_final_snapshot = true
}

resource "aws_security_group" "instance" {
    name = "health_med_sg"
    ingress {
        from_port   = 5432
        to_port     = 5432
        protocol    = "tcp"
        cidr_blocks = ["0.0.0.0/0"]
    }
}