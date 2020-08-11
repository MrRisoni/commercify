<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class AddTaxCodeToTaxRules extends AbstractMigration
{
    /**
     * Change Method.
     *
     * Write your reversible migrations using this method.
     *
     * More information on writing migrations is available here:
     * https://book.cakephp.org/phinx/0/en/migrations.html#the-change-method
     *
     * Remember to call "create()" or "update()" and NOT "save()" when working
     * with the Table class.
     */
    public function change(): void
    {
        $addTaxCodeToTaxRules = $this->table('shop_tax_rules');
        $addTaxCodeToTaxRules->addColumn('tax_code_id', 'biginteger', ['after' => 'shop_id','signed' => false])
        ->addForeignKey('tax_code_id', 'shop_tax_code_names', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])->update();
        

       $addTaxCodeToTaxRulesRegion = $this->table('shop_tax_region_rules');
       $addTaxCodeToTaxRulesRegion->addColumn('tax_code_id', 'biginteger', ['after' => 'shop_id','signed' => false])
       ->addForeignKey('tax_code_id', 'shop_tax_code_names', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])->update();


    }
}
