package com.mayab.calidad.funcionales;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestCrudAdd.class, TestCrudRead.class, TestCrudUpdate.class,TestCrudDelete.class })
public class AllTests {

}
