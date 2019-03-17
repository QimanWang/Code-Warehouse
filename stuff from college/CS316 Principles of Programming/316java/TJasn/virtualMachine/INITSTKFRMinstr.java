package TJasn.virtualMachine;

import TJasn.TJ;

import static TJasn.virtualMachine.CodeInterpreter.*;

public class INITSTKFRMinstr extends OneOperandInstruction {

  void execute () throws VirtualMachineHaltException
  {
     /* ???????? *///done

    TJ.data[ASP++ - POINTERTAG] = FP;
    FP = ASP-1;
    ASP += operand;
  }

  public INITSTKFRMinstr (int locationsNeededForLocalVars)
  {
    super(locationsNeededForLocalVars, "INITSTKFRM");
  }
}

