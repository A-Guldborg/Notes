# Exercise Problems

## Chapter 1

### 1.1 (page 50)

Consider a student information system (SIS) in which students provide a university student number (USN) and a card for account access. Give examples of confidentiality, integrity, and availability requirements associated with the system and, in each case, indicate the degree of the importance of the requirement.

#### Confidentiality

Data should be private, e.g., special needs, exam scores etc. (Moderate)

#### Integrity

Data should not be changeable - students should not be able to change the scores from their exams. Adversaries should not be able to alter course/class registrations. (High)

#### Availability

Students should be able to get access to the university with their card. (Low)

Students should be able to access their exam grades when they need them for e.g. applications or similar check-ups.

### 1.4 (page 50)

For each of the following assets, assign a low, moderate, or high impact level for the loss of confidentiality, availability, and integrity, respectively. Justify your answers.
1. An organization managing public information on its Web server.
    - Confidentiality (Low): The data is public so there is probably not any secret data that needs to be kept confidential.
    - Availability (Moderate): Depending on the importance of the information, the impact level might differ - but, if the system is unavailable, it will mostly damage the reputation of the organization. 
    - Integrity (High): If the data, which is public, is not correct, it will damage the reputation of the organization highly and possibly leave the organization vulnerable for lawsuits.
2. A law enforcement organization managing extremely sensitive investigative information.
    - Confidentiality (High): The information is by nature very sensitive and unauthorized access can damage individuals that do not themselves have access to the data or give terror groups information that can lead to prison escapes or attacks on people who are in police protection.
    - Availability (Moderate): Compared to the remaining two, this is probably not as important but without the information available, officers, prison guards etc. might not have the data required to act upon and might delay important things such as releases or transfers of inmates, requirements for prison protection, officer access to information on private vehicles etc. 
    - Integrity (High): It should not be possible for adversaries to change the data relating to criminals in the law enforcement system, such as removing evidence or changing judicial decisions.
3. A financial organization managing routine administrative information (not privacyrelated information).
    - Confidentiality (High): Financial information, such as account or transaction amounts should be kept confidential to decrease risk for individuals based on their wealth.
    - Availability (Moderate): Compared to the other two, it is more important to make sure the financial information is private and correct rather than making sure it is possible t o make payments etc.
    - Integrity (High): It should not be possible to alter financial information such as transaction or account amounts. 
4. An information system used for large acquisitions in a contracting organization contains both sensitive, pre-solicitation phase contract information and routine administrative information. Assess the impact for the two data sets separately and the information system as a whole.
    - Contract information
        - Confidentiality (High): If the data from contracts are not kept private to the parties involved, the company's reputation will be severely damaged. This information leads to possible financial gains from investors who should not have this knowledge etc.
        - Availability (Low): Unavailable data will cause delays but will not cause havok.
        - Integrity (Moderate): The data should not be alterable for adversaries, but in case it is the data will be verified prior to contract deals.
    - Routine administrative information
        - Confidentiality ():
        - Availability (): 
        - Integrity ():
    - Overall system
        - Confidentiality ():
        - Availability (): 
        - Integrity ():
5. A power plant contains a SCADA (supervisory control and data acquisition) system controlling the distribution of electric power for a large military installation. The SCADA system contains both real-time sensor data and routine administrative information. Assess the impact for the two data sets separately and the information system as a whole.
    - Confidentiality ():
    - Availability (): 
    - Integrity (): 

### 1.5 (page 51)

Consider the following general code for allowing access to a resource:
```
DWORD dwRet = IsAccessAllowed(...);
if (dwRet == ERROR_ACCESS_DENIED) {
// Security check failed.
// Inform user that access is denied.
} else {
// Security check OK.
}
```
1. Explain the security flaw in this program.
    The user is assumed access to the data, unless explicitly told not to get access. In other words, if the IsAccessAllowed() call fails with error 500, then it will not result in a ERROR_ACCESS_DENIED (or at least this depends on the implementation of that code instead), leaving a vulnerability.
2. Rewrite the code to avoid the flaw.
Hint: Consider the design principle of fail-safe defaults.

```
DWORD dwRet = IsAccessAllowed(...);
if (dwRet == ACCESS_GRANTED) {
    // Security check OK.
} else {
    // Security check failed.
    // Inform user that access is denied.
}
```

### Multi-step authentication at ITU (LearnIT)

- The IT department is considering to introduce multi-step authentication to protect their users. Before applying the new policy, the department asks for your advice. Multi-step authentication requires the user to choose any two of the following authentication methods at each login.

Password
Fingerprint recognition
Student card
SMS via phone
Call via phone
Email confirmation
Iris Scanning
Give a recommendation: should this policy be adopted or not? Use security principles to motivate your recommendation.

#### Simplicity 

Complicated login methods such as iris scanning or fingerprint recognition can be difficult to keep safe as there might be small issues that require extensive knowledge of irises or fingerprints.

#### Minimum exposure

Many interfaces needs to be protected, many different mechanisms can be open for vulnerabilities.

#### Psychological acceptability

Some of the login methods are complicated to use such as email confirmation and call via phone and would likely not be adopted. This could also mean that flaws would not be found by anyone else than adversaries. However, some of the others might make access easier to different things just by scanning a finger and an iris, making users accept the login methods without too much hassle.

In general, the different principles speaks against the proposed authentication method, but if users actually implement it would possibly benefit, though increase cost of protection for having multiple methods that can be vulnerable to attacks. Thus it could be a solution in which only a limited set of these authentication methods could be used instead of all of them.
