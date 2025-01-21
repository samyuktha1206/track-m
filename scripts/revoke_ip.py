import boto3
import sys
import os


def revoke_ip(ip):
    security_group_id=os.getenv("SECURITY_GROUP_ID")  # Replace with your EC2 security group ID
    region=os.getenv("AWS_DEFAULT_REGION")
    SSH_PORT = 22
    
    if not region or not security_group_id:
        raise ValueError("AWS_DEFAULT_REGION or SECURITY_GROUP_ID is not set in the environment variables.")
    
    ec2_client = boto3.client("ec2", region_name=region)
    try:
        ec2_client.revoke_security_group_ingress(
            GroupId=security_group_id,
            IpProtocol="tcp",
            FromPort=SSH_PORT,
            ToPort=SSH_PORT,
            CidrIp=f"{ip}/32",
        )
        print(f"Revoked IP: {ip}/32 for SSH access")
    except Exception as e:
        print(f"Error revoking IP: {e}")

if __name__ == "__main__":
    if len(sys.argv) < 2:
        print("Usage: python revoke_ip.py <runner_ip>")
    else:
        revoke_ip(sys.argv[1])
