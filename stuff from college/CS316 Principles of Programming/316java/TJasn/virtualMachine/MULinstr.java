package TJasn.virtualMachine;

import static TJasn.virtualMachine.CodeInterpreter.ESP;
import static TJasn.virtualMachine.CodeInterpreter.EXPRSTACK;

public class MULinstr extends ZeroOperandInstruction {

  void execute () throws VirtualMachineHaltException
  {
      /* ???????? *///done
    EXPRSTACK[(--ESP - 1)] *=  EXPRSTACK[ESP];
  }

  public MULinstr ()
  {
    super("MUL");
  }
}

