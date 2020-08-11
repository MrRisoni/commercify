<?php
declare(strict_types=1);

use Phinx\Migration\AbstractMigration;

final class TaxRegionsRules extends AbstractMigration
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
        $taxRegionRulesExceptions = $this->table('shop_tax_region_rules', ['signed' => false]);
        $taxRegionRulesExceptions->addColumn('shop_id', 'biginteger', ['signed' => false])
        ->addColumn('region_id', 'biginteger', ['signed' => false])
        ->addColumn('title', 'string', ['limit' => 52])
        ->addColumn('country_code', 'string', ['limit' => 3])
        ->addColumn('flat_cost', 'decimal', ['precision' => 10,'scale'=>2])
        ->addColumn('rate', 'decimal', ['precision' => 10,'scale'=>2])
        ->addColumn('created', 'datetime')
        ->addColumn('updated', 'datetime', ['null' => true])
        ->addColumn('active', 'boolean')
        ->addForeignKey('shop_id', 'shops', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->addForeignKey('region_id', 'globe_regions', 'id', ['delete'=> 'RESTRICT', 'update'=> 'RESTRICT'])
        ->create();
    }
}
