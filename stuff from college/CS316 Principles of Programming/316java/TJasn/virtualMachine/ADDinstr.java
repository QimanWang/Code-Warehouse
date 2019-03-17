package TJasn.virtualMachine;

import static TJasn.virtualMachine.CodeInterpreter.ESP;
import static TJasn.virtualMachine.CodeInterpreter.EXPRSTACK;

public class ADDinstr extends ZeroOperandInstruction {

  void execute () throws VirtualMachineHaltException
  {
      /* ???????? */ //done
    EXPRSTACK[--ESP-1] += EXPRSTACK[ESP];
  }

  public ADDinstr ()
  {
    super("ADD");
  }
}

