using System;
using Sandbox.Hospital;

var anesthetist = new Anesthetist
{
    Name = "Higor",
    Id = "1",
    AdmissionDate = new DateTime(),
    Crm = "1234123"
};

var doc = (Doctor)anesthetist;
anesthetist.Operate();
doc.Operate();