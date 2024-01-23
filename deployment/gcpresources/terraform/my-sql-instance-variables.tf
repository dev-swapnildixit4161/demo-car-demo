# database instance settings
variable db_version {
  description = "The version of of the database. For example, MYSQL_5_6 or MYSQL_5_7"
  default     = "MYSQL_8_0"
}

variable db_tier {
  description = "The machine tier (First Generation) or type (Second Generation). Reference: https://cloud.google.com/sql/pricing"
  default     = "db-f1-micro"
}

variable db_activation_policy {
  description = "Specifies when the instance should be active. Options are ALWAYS, NEVER or ON_DEMAND"
  default     = "ALWAYS"
}

variable db_disk_autoresize {
  description = "Second Generation only. Configuration to increase storage size automatically."
  default     = true
}

variable db_disk_size {
  description = "Second generation only. The size of data disk, in GB. Size of a running instance cannot be reduced but can be increased."
  default     = 10
}

variable db_disk_type {
  description = "Second generation only. The type of data disk: PD_SSD or PD_HDD"
  default     = "PD_SSD"
}


variable db_instance_access_cidr {
  description = "The IPv4 CIDR to provide access the database instance"
  default     = "0.0.0.0/0"
}

# database settings

# orders database
variable orders_db_name {
  description = "Name of the default database to create"
  default     = "orders_db"
}

# inventory database
variable inventory_db_name {
  description = "Name of the default database to create"
  default     = "inventory_db"
}

# payment database
variable payment_db_name {
  description = "Name of the default database to create"
  default     = "payment_db"
}

# shipment database
variable shipment_db_name {
  description = "Name of the default database to create"
  default     = "shipment_db"
}

# cart database
variable cart_db_name {
  description = "Name of the default database to create"
  default     = "cart_db"
}

# user settings
variable user_name {
  description = "The name of the default user"
  default     = "dbadmin"
}

variable user_password {
  description = "The password for the default user. If not set, a random one will be generated and available in the generated_user_password output variable."
  default     = "JavaDBs#password2023"
}
