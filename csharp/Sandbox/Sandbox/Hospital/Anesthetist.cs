using System;

namespace Sandbox.Hospital
{
    public class Anesthetist : Doctor
    {
        public override void Operate()
        {
            Console.WriteLine("Operate Anesthetist");
        }

    }
}