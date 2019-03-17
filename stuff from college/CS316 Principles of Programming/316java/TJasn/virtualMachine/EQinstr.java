package TJasn.virtualMachine;

import static TJasn.virtualMachine.CodeInterpreter.*;

public class EQinstr extends ZeroOperandInstruction {

  void execute ()
  {
    /* ???????? *///done
    EXPRSTACK[--ESP-1] = (EXPRSTACK[ESP-1] == EXPRSTACK[ESP]) ? 1 : 0;
  }

  public EQinstr ()
  {
    super("EQ");
  }
}

