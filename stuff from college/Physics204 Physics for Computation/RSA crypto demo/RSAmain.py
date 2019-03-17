import random
import itertools
import primes_list
import fractions

#this method will generate prime number
def generate_random_prime_number(number_of_bits, test_if_prime):
    get_random_t_value = lambda: random.getrandbits(number_of_bits) | 1 << number_of_bits | 1
    p = get_random_t_value()
    for i in itertools.count(1):
        if test_if_prime(p):
            return p
        else:
            if i % (number_of_bits * 2) == 0:
                p = get_random_t_value()
            else:
                p += 2
#add two to a odd will ensure it's still odd, because all prime is odd

#simple_prime_test, will return true if it's a prime.
def simple_prime_test(n):
    if n % 2 == 0:
        return n == 2
    if n % 3 == 0:
        return n == 3
    k = 6
    while (k - 1) ** 2 <= n:
        if n % (k - 1) == 0 or n % (k + 1) == 0:
            return False
        k += 6
    return True

#Rabin_Miller_test_if_prime_method
#k random number of witness attempts, return true if it's a compoite number
#when k is large , false positive is extremely unlikely
def Rabin_Miller_test_if_prime(n, k=20):
    b = basic_prime_test_using_file(n, K=100)
    if b is not None:
        return b

    m = n - 1
    s = 0
    while m % 2 == 0:
        s += 1
        m //= 2
    liars = set()
    get_new_x = lambda: random.randint(2, n - 1)
    while len(liars) < k:
        x = get_new_x()
        while x in liars:
            x = get_new_x()
        xi = pow(x, m, n)
        witness = True
        if xi == 1 or xi == n - 1:
            witness = False
        else:
            for __ in range(s - 1):
                xi = (xi ** 2) % n
                if xi == 1:
                    return False
                elif xi == n - 1:
                    witness = False
                    break
            xi = (xi ** 2) % n
            if xi != 1:
                return False
        if witness:
            return False
        else:
            liars.add(x)
    return True

#basic_prime_test_using_file
#this uses the prime_list which contains all primes up 100,000
#this will boost the prime test even faster
def basic_prime_test_using_file(n, K=-1):
    if n % 2 == 0:
        return n == 2
    for p in primes_list.less_than_hundred_thousand[:K]:
        if n % p == 0:
            return n == p
    return None

#power function that runs O(log(m)) when calculating (x^m)mod (n)
def power(x, m, n):
    a = 1
    while m > 0:
        if m % 2 == 1:
            a = (a * x) % n
        x = (x * x) % n
        m //= 2
    return a

#rsa code
def extended_gcd(a, b):
#Returns pair (x, y) such that xa + yb = gcd(a, b)
    x, lastx, y, lasty = 0, 1, 1, 0
    while b != 0:
        q, r = divmod(a, b)
        a, b = b, r
        x, lastx = lastx - q * x, x
        y, lasty = lasty - q * y, y
    return lastx, lasty

#Find the multiplicative inverse of e mod n
def multiplicative_inverse(e, n):
    x, y = extended_gcd(e, n)
    if x < 0:
        return n + x
    return x

#generate random key
def rsa_generate_key(number_of_bits):
    p = generate_random_prime_number(number_of_bits / 2,Rabin_Miller_test_if_prime)
    q = generate_random_prime_number(number_of_bits / 2,Rabin_Miller_test_if_prime)

    #make sure p and q are not the same
    while q == p:
        q = generate_random_prime_number(number_of_bits / 2,simple_prime_test)
    n = (p * q)
    phi = (p - 1) * (q - 1)
    # Here we pick a random e, but a fixed value for e can also be used.
    while True:
        e = random.randint(3, phi - 1)
        if fractions.gcd(e, phi) == 1:
            break
    d = multiplicative_inverse(e, phi)
    return (n, e, d)


def rsa_encrypt(message, n, e):
    return power(message, e, n)


def rsa_decrypt(cipher, n, d):
    return power(cipher, d, n)


#main
print("Testing if prime using simple test")
for b in [8, 16, 32, 44, 45, 46,47]:
    print ("Generating %d-bit prime: " % b)
    print (generate_random_prime_number(b, simple_prime_test))
#fast test
print("Testing if prime using rabin miller")
for b in [8, 16, 32, 44, 45, 46,47,60,1100]:
    print ("Generating %d-bit prime: " % b)
    print (generate_random_prime_number(b, Rabin_Miller_test_if_prime))

#faster test
#for b in [2 ** i for i in range(3, 12)]:
#     print ("Generating %d-bit prime: " % b)
#     print (generate_random_prime_number(b, Rabin_Miller_test_if_prime))


#less secure
print ""
print "example of a 8 bit key"
n, e, d = rsa_generate_key(8)
print "n:",n,"e:",e,"d:",d
message = 223
print "encrypting message:",message
cipher = rsa_encrypt(message, n, e)
print "cipher:", cipher
decrypted_message = rsa_decrypt(cipher, n, d)
print "decrypted message", decrypted_message

#very secure
print ""
print "example of a 64 bit key"
n, e, d = rsa_generate_key(64)
print "n:",n,"e:",e,"d:",d
message = 74328042423
print ("encrypting message:",message)
cipher = rsa_encrypt(message, n, e)
print ("cipher:", cipher)
decrypted_message = rsa_decrypt(cipher, n, d)
print ("decrypted message", decrypted_message)
