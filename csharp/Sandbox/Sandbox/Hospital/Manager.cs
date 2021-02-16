using System;

namespace Sandbox.Hospital
{
    public class Manager : Employee
    {
        public string Cra { get; init; }

        public void RunPayment() =>
            Console.WriteLine("Payment");

    }
}