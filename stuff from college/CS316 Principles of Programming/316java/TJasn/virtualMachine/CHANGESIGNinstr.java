package TJasn.virtualMachine;

import static TJasn.virtualMachine.CodeInterpreter.*;

public class CHANGESIGNinstr extends ZeroOperandInstruction {

  void execute () throws VirtualMachineHaltException
  {
      /* ???????? *///done
    EXPRSTACK[ESP - 1] *=  -1;
  }

  public CHANGESIGNinstr ()
  {
    super("CHANGESIGN");
  }
}

