import rsa

KEY_SIZE = 512

if __name__ == '__main__':
    alice_message_to_bob = "Hello, Bob 😀"
    print(f"Alice: {alice_message_to_bob}")

    # 👦 Generate Bob Keys
    (bob_public_key, bob_private_key) = rsa.newkeys(KEY_SIZE)

    # 👧 Alice has Bob`s public key and encrypt the message
    message_in_bytes = alice_message_to_bob.encode('utf8')
    encrypted_message = rsa.encrypt(message_in_bytes, bob_public_key)

    # 👦 Bob receive the message and decrypt it
    message_in_bytes = rsa.decrypt(encrypted_message, bob_private_key)
    message = message_in_bytes.decode("utf8")
    print(f"Bob: {message}")
