using System;

namespace Sandbox.Hospital
{
    public abstract class Employee : Person
    {
        public string Id { get; init; }
        public DateTime AdmissionDate { get; init; }
    }
}