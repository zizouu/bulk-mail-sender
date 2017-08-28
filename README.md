# bulk-mail-sender
## v0.0.1 - 2017/08/28
### 1. Summary
- Save eml to local path, send eml by smtp.
- Can adjust the number of save/send eml.
### 2. Local Save
- Save eml to local path with simple header - From, To, Subject, Content(text/html, utf-8), Date(now).
- File name created by current time.
- If use random subject and content then create 10 length random string subject and 30 length random string content.
### 3. Smtp Send
- Send mail by smtp host. one Recipient.
- Can use send limit time per seconds (limit time).
- Can use the number of transmit eml per limit time (limit count).
- Support multi thread.
  
