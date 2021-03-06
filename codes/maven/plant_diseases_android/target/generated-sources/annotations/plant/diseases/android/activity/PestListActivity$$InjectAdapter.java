// Code generated by dagger-compiler.  Do not edit.
package plant.diseases.android.activity;

import dagger.MembersInjector;
import dagger.internal.Binding;
import dagger.internal.Linker;
import java.util.Set;
import javax.inject.Provider;

/**
 * A {@code Binding<PestListActivity>} implementation which satisfies
 * Dagger's infrastructure requirements including:
 *
 * Owning the dependency links between {@code PestListActivity} and its
 * dependencies.
 *
 * Being a {@code Provider<PestListActivity>} and handling creation and
 * preparation of object instances.
 *
 * Being a {@code MembersInjector<PestListActivity>} and handling injection
 * of annotated fields.
 */
public final class PestListActivity$$InjectAdapter extends Binding<PestListActivity>
    implements Provider<PestListActivity>, MembersInjector<PestListActivity> {
  private Binding<com.zyt.android.dbbase.AbsDaoFactory> daoFactory;
  private Binding<BaseActivity> supertype;

  public PestListActivity$$InjectAdapter() {
    super("plant.diseases.android.activity.PestListActivity", "members/plant.diseases.android.activity.PestListActivity", NOT_SINGLETON, PestListActivity.class);
  }

  /**
   * Used internally to link bindings/providers together at run time
   * according to their dependency graph.
   */
  @Override
  @SuppressWarnings("unchecked")
  public void attach(Linker linker) {
    daoFactory = (Binding<com.zyt.android.dbbase.AbsDaoFactory>) linker.requestBinding("com.zyt.android.dbbase.AbsDaoFactory", PestListActivity.class, getClass().getClassLoader());
    supertype = (Binding<BaseActivity>) linker.requestBinding("members/plant.diseases.android.activity.BaseActivity", PestListActivity.class, getClass().getClassLoader(), false, true);
  }

  /**
   * Used internally obtain dependency information, such as for cyclical
   * graph detection.
   */
  @Override
  public void getDependencies(Set<Binding<?>> getBindings, Set<Binding<?>> injectMembersBindings) {
    injectMembersBindings.add(daoFactory);
    injectMembersBindings.add(supertype);
  }

  /**
   * Returns the fully provisioned instance satisfying the contract for
   * {@code Provider<PestListActivity>}.
   */
  @Override
  public PestListActivity get() {
    PestListActivity result = new PestListActivity();
    injectMembers(result);
    return result;
  }

  /**
   * Injects any {@code @Inject} annotated fields in the given instance,
   * satisfying the contract for {@code Provider<PestListActivity>}.
   */
  @Override
  public void injectMembers(PestListActivity object) {
    object.daoFactory = daoFactory.get();
    supertype.injectMembers(object);
  }

}
