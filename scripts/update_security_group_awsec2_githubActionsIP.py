import requests
import boto3

SECURITY_GROUP_ID = "sg-070984badcda1d438"  # Replace with your EC2 security group ID
AWS_DEFAULT_REGION = "eu-north-1"

def fetch_github_actions_ips():
    print("Fetching GitHub Actions IP ranges...")
    response = requests.get("https://api.github.com/meta")
    response.raise_for_status()
    return response.json().get("actions", [])

def update_security_group(ip_ranges):
    ec2_client = boto3.client("ec2", region_name=AWS_DEFAULT_REGION)

# Update Security Group
def update_security_group(ip_ranges):
    ec2_client = boto3.client("ec2", region_name=AWS_DEFAULT_REGION)

    # Revoke all existing ingress rules for SSH
    print("Revoking existing rules...")
    security_group = ec2_client.describe_security_groups(GroupIds=[SECURITY_GROUP_ID])
    for permission in security_group["SecurityGroups"][0]["IpPermissions"]:
        if permission["IpProtocol"] == "tcp" and permission["FromPort"] == 22:
            ec2_client.revoke_security_group_ingress(
                GroupId=SECURITY_GROUP_ID,
                IpPermissions=[permission]
            )

    # Add new rules for GitHub Actions IP ranges
    # print("Adding new rules...")
    # for ip_range in ip_ranges:
    #     ec2_client.authorize_security_group_ingress(
    #         GroupId=SECURITY_GROUP_ID,
    #         IpProtocol="tcp",
    #         FromPort=22,
    #         ToPort=22,
    #         CidrIp=ip_range,
    #     )
    #     print(f"Added {ip_range}")

if __name__ == "__main__":
    try:
        ip_ranges = fetch_github_actions_ips()
        update_security_group(ip_ranges)
        print("Security group updated successfully.")
    except Exception as e:
        print(f"Error: {e}")