# bulk-mail-sender
## v0.0.1 - 2017/08/28
- Save eml to local path with simple header - From, To, Subject, Content(text/html, utf-8), Date(now)
  - file name created by current time
  - If use random subject and content then create 10 length random string subject and 30 length random string content.
- Send mail by smtp host. one Recipient
  - can use send limit time per seconds (limit time)
  - can use the number of send eml per limit time (limit count)
