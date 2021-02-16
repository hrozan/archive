namespace Sandbox.Hospital
{
    public abstract class Doctor : Employee
    {
        public string Crm { get; init; }

        public abstract void Operate();
    }
}